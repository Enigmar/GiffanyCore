package de.linzn.aiCore.inputProcessing.network;

import de.linzn.aiCore.App;
import de.linzn.javaSocket.server.interfaces.IServerMask;

public class SocketTemplate implements IServerMask {


	@Override
	public void serverSchedulerAsync(Runnable runnable) {
		// TODO Auto-generated method stub
		App.appInstance.runTaskAsync(runnable);

	}

	@Override
	public void serverSchedulerSync(Runnable runnable) {
		// TODO Auto-generated method stub
		App.appInstance.runTaskSync(runnable);
	}

	@Override
	public void debugInfo(String string) {
		// TODO Auto-generated method stub
		App.logger(string);

	}

}
