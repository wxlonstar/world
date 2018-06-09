package org.aimfd.world.system.match.manager.module;

import java.util.ArrayList;
import java.util.List;

import org.aimfd.world.AData;
import org.aimfd.world.PlanetCache;
import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.enterprise.IEnterpriseInternal;
import org.aimfd.world.system.match.data.IMatchData;
import org.aimfd.world.system.room.ISystemRoomPublic;

public class BeginMatchModule {

	private IMatchData matchData;
	private ISystemRoomPublic roomPublic;

	public BeginMatchModule() {
		matchData = AData.getSystemAllData().getSystemPeaceData().getMatchData();
		roomPublic = AData.getASystem().getSystemManager(ISystemRoomPublic.class);
	}

	public void beginMatch(String account) {
		if (matchData.contains(account)) {
			return;
		}

		// 如果已经在房间中则返回
		if (roomPublic.atRoom(account)) {
			return;
		}

		matchData.addAccount(account);

		// 如果到人数了，则把人加入到房间中，并从原来的位置移除
		int count = 4;
		if (matchData.getQueueLength() >= count) {
			List<String> accounts = new ArrayList<>(count);
			for (int i = 0; i < 4; i++) {
				accounts.add(matchData.poll());
			}

			// 取出先排进去的人并开始游戏
			Planet planet = PlanetCache.getIdlePlanet();
			IEnterpriseInternal enterpriseInternal = planet.getPlanetManagerInterface(IEnterpriseInternal.class);
			enterpriseInternal.setEnterprises(accounts);

		}

	}

}
