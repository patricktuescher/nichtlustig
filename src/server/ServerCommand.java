package server;

import client.Command;

public enum ServerCommand implements Command {
	HANDSHAKE, CONNECTED, ERROR_CONNECTION, DISCONNECTED, REJECT_CONNECTION
}
