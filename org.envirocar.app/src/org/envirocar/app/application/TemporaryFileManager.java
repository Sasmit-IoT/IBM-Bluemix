/* 
 * enviroCar 2013
 * Copyright (C) 2013  
 * Martin Dueren, Jakob Moellers, Gerald Pape, Christopher Stephan
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 * 
 */
package org.envirocar.app.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.envirocar.app.exception.InvalidObjectStateException;
import org.envirocar.app.logging.Logger;
import org.envirocar.app.util.Util;

import android.content.Context;

public class TemporaryFileManager {

	private static final Logger logger = Logger.getLogger(TemporaryFileManager.class);
	
	private static TemporaryFileManager instance;
	private Context context;
	private List<File> temporaryFiles = new ArrayList<File>();

	private TemporaryFileManager(Context applicationContext) {
		this.context = applicationContext;
	}

	public static synchronized void init(Context applicationContext) {
		instance = new TemporaryFileManager(applicationContext);
	}
	
	public static synchronized TemporaryFileManager instance() throws InvalidObjectStateException {
		if (instance == null) {
			throw new InvalidObjectStateException("No instance available!");
		}
		return instance;
	}
	
	public void shutdown() {
		for (File f : this.temporaryFiles) {
			try {
				f.delete();
			} catch (RuntimeException e) {
				logger.warn(e.getMessage(), e);
			}
		}
	}

	public File createTemporaryFile() {
		File result = new File(Util.resolveCacheFolder(context), UUID.randomUUID().toString());
		
		addTemporaryFile(result);
		
		return result;
	}

	private synchronized void addTemporaryFile(File result) {
		this.temporaryFiles .add(result);
	}

}
