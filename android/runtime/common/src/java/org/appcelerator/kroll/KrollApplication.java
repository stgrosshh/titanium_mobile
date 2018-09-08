/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2012-2015 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package org.appcelerator.kroll;

import org.appcelerator.kroll.common.CurrentActivityListener;
import org.appcelerator.kroll.common.TiDeployData;
import org.appcelerator.kroll.util.TiTempFileHelper;

import android.app.Activity;

/**
 * An interface for things Kroll needs from the application instance
 */
public interface KrollApplication {
	boolean DEFAULT_RUN_ON_MAIN_THREAD = false;

	public int getThreadStackSize();

	public Activity getCurrentActivity();

	public void waitForCurrentActivity(CurrentActivityListener l);

	public TiTempFileHelper getTempFileHelper();

	public TiDeployData getDeployData();

	public boolean isFastDevMode();

	public String getAppGUID();

	public boolean isDebuggerEnabled();

	public boolean runOnMainThread();

	public void dispose();

	public String getDeployType();

	public String getDefaultUnit();

	public String getSDKVersion();

	public void cancelTimers();

	public void loadAppProperties();
}
