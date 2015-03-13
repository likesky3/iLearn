package org.work.basic;

public class Hex2UnsignedInt {

	public static void main(String[] args) {
		String sh_requestID = "8042d5f3aaf620893cc23d9b7bd1eb47";
		StringBuilder webCache_requestID = new StringBuilder();
		for (int i = 0, j = 8; j < 33; j +=8) {
			int tmp = Integer.parseUnsignedInt(sh_requestID.substring(i, j),16);
			int tmp2r = Integer.reverseBytes(tmp); //in webCache, little endian: parseInt as f3d54280
			webCache_requestID.append(tmp2r & 0x0ffffffffl).append('_');
			i = j;
		}
		webCache_requestID.deleteCharAt(webCache_requestID.length() - 1);
		System.out.println(webCache_requestID.toString());
	}

}
