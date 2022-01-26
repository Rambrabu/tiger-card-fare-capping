package com.tc.farecapping.model;

import java.util.Arrays;

public class FromToZoneKeyConfig {
	
	  	private Integer key1;
	    private Integer key2;
	    Integer[] keys;

	    public FromToZoneKeyConfig(Integer... keys) {
	        this.keys = keys;
	    }

	    @Override
	    public int hashCode() {
	        int i = 1;
	        StringBuffer keysChain = new StringBuffer();
	        for (Integer key : keys) {
	            keysChain.append("key" + i + key + "|");
	            i++;
	        }
	        return keysChain.toString().hashCode();
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null || !(obj instanceof FromToZoneKeyConfig)) {
	            return false;
	        }
	        FromToZoneKeyConfig fromToZoneKeyConfig = (FromToZoneKeyConfig) obj;
	        return Arrays.equals(fromToZoneKeyConfig.keys, this.keys);
	    }

		@Override
		public String toString() {
			return "FromToZoneKeyConfig [keys=" + keys  + "]";
		}

}
