package main.Tokens;

	public interface ScannerIntf {
		public String getScannerInput();
		public void setScannerInput(String input);
		public String[] getErrorArray();
		public Token[] getTokenArray();
}
