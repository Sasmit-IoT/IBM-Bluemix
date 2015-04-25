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

package org.envirocar.app.exception;

import org.envirocar.app.storage.DbAdapter;

/**
 * This exception is thrown when there are no tracks in the local database.
 * 
 * @author jakob
 * @deprecated replaced by invariants of Interface {@link DbAdapter#getLastUsedTrack()}
 */
@Deprecated
public class TracksException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5754700912732803345L;

	@Deprecated
	public TracksException(String e) {
		super(e);
	}

}
