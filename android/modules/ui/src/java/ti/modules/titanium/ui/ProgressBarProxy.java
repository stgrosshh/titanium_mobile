/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2016 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.modules.titanium.ui;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;

import ti.modules.titanium.ui.widget.TiUIProgressBar;
import android.app.Activity;
// clang-format off
@Kroll.proxy(creatableInModule = UIModule.class,
	propertyAccessors = {
		"min",
		"max",
		TiC.PROPERTY_VALUE,
		TiC.PROPERTY_MESSAGE,
		TiC.PROPERTY_COLOR
})
// clang-format on
public class ProgressBarProxy extends TiViewProxy
{
	public ProgressBarProxy()
	{
		super();
	}

	@Override
	public TiUIView createView(Activity activity)
	{
		return new TiUIProgressBar(this);
	}

	@Override
	public String getApiName()
	{
		return "Ti.UI.ProgressBar";
	}
}
