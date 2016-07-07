package Vorlage;

public enum ServerCommand implements Command {
	HANDSHAKE, CONNECTED, ERROR_CONNECTION, DISCONNECTED, REJECT_CONNECTION
}
