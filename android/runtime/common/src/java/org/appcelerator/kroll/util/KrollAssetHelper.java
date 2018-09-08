/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package org.appcelerator.kroll.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class KrollAssetHelper
{
	private static final String TAG = "TiAssetHelper";
	private static AssetManager assetManager;
	private static String packageName, cacheDir;
	private static AssetCrypt assetCrypt;

	public interface AssetCrypt {
		String readAsset(String path);
		String[] getAssetPaths();
	}

	public static void setAssetCrypt(AssetCrypt assetCrypt)
	{
		KrollAssetHelper.assetCrypt = assetCrypt;
	}

	public static void init(Context context)
	{
		KrollAssetHelper.assetManager = context.getAssets();
		KrollAssetHelper.packageName = context.getPackageName();
		KrollAssetHelper.cacheDir = context.getCacheDir().getAbsolutePath();
	}

	public static String readAsset(String path)
	{
		String resourcePath = path.replace("Resources/", "");

		if (assetCrypt != null) {
			String asset = assetCrypt.readAsset(resourcePath);
			if (asset != null) {
				return asset;
			}
		}

		try {
			if (assetManager == null) {
				Log.e(TAG, "AssetManager is null, can't read asset: " + path);
				return null;
			}

			InputStream in = assetManager.open(path);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte buffer[] = new byte[1024];
			int count = 0;

			while ((count = in.read(buffer)) != -1) {
				if (out != null) {
					out.write(buffer, 0, count);
				}
			}

			return out.toString();

		} catch (IOException e) {
			Log.e(TAG, "Error while reading asset \"" + path + "\":", e);
		}

		return null;
	}

	public static String[] getEncryptedAssetPaths()
	{
		if (assetCrypt != null) {
			return assetCrypt.getAssetPaths();
		}
		return null;
	}

	public static String readFile(String path)
	{
		try {
			FileInputStream in = new FileInputStream(path);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte buffer[] = new byte[1024];
			int count = 0;

			while ((count = in.read(buffer)) != -1) {
				if (out != null) {
					out.write(buffer, 0, count);
				}
			}

			return out.toString();

		} catch (FileNotFoundException e) {
			Log.e(TAG, "File not found: " + path, e);

		} catch (IOException e) {
			Log.e(TAG, "Error while reading file: " + path, e);
		}

		return null;
	}

	public static boolean assetExists(String path)
	{
		if (assetCrypt != null) {
			String asset = assetCrypt.readAsset(path.replace("Resources/", ""));
			if (asset != null) {
				return true;
			}
		}
		if (assetManager != null) {
			try {
				return assetManager != null && assetManager.open(path) != null;
			} catch (IOException e) {
			}
		}
		return false;
	}

	public static String getPackageName()
	{
		return packageName;
	}

	public static String getCacheDir()
	{
		return cacheDir;
	}
}
