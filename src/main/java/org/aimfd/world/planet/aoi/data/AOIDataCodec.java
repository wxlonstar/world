package org.aimfd.world.planet.aoi.data;

import java.util.Map;

import org.aimfd.base.IDataJSONCodec;
import org.aimfd.world.planet.aoi.data.unit.IAOIUnitData;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AOIDataCodec implements IDataJSONCodec {
	protected Map<Integer, IAOIUnitData> aoiUnitMap;

	@Override
	public JSONObject encode() {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		json.put("aoiUnitMap", array);
		for (IAOIUnitData unitData : aoiUnitMap.values()) {
			IDataJSONCodec unitDataCodec = (IDataJSONCodec) unitData;
			array.add(unitDataCodec.encode());
		}
		return json;
	}

	@Override
	public void decode(JSONObject source) {

	}

}
