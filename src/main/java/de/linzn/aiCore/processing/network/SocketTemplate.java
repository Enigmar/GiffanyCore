package de.linzn.aiCore.processing.network;

import de.linzn.aiCore.App;
import de.linzn.javaSocket.server.interfaces.IMask;

public class SocketTemplate implements IMask {

	@Override
	public void schedulerAsync(Runnable runnable) {
		// TODO Auto-generated method stub
		App.appInstance.runTaskAsync(runnable);

	}

	@Override
	public void schedulerSync(Runnable runnable) {
		// TODO Auto-generated method stub
		App.appInstance.runTaskSync(runnable);
	}

	@Override
	public void debugInfo(String string) {
		// TODO Auto-generated method stub
		App.logger(string);

	}

}
