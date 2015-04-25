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
package org.envirocar.app.dao;

import org.envirocar.app.dao.exception.TermsOfUseRetrievalException;
import org.envirocar.app.model.TermsOfUse;
import org.envirocar.app.model.TermsOfUseInstance;

public interface TermsOfUseDAO {

	/**
	 * @return the object providing the available terms of use list
	 * @throws TermsOfUseRetrievalException
	 */
	public TermsOfUse getTermsOfUse() throws TermsOfUseRetrievalException;
	
	/**
	 * @param id the id of the terms of use instance
	 * @return the instance for the given id
	 * @throws TermsOfUseRetrievalException
	 */
	public TermsOfUseInstance getTermsOfUseInstance(String id) throws TermsOfUseRetrievalException;
	
}
