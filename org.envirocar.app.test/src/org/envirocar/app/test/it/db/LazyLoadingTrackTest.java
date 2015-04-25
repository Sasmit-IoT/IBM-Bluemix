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
package org.envirocar.app.test.it.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import junit.framework.Assert;

import org.envirocar.app.exception.MeasurementsException;
import org.envirocar.app.storage.DbAdapterImpl;
import org.envirocar.app.storage.Measurement;
import org.envirocar.app.storage.Track;

import android.test.InstrumentationTestCase;

public class LazyLoadingTrackTest extends InstrumentationTestCase {
	
	private static final int COUNT = 10;
	private Random random = new Random();

	public void testLazyLoading() throws InterruptedException, InstantiationException {
		if (DbAdapterImpl.instance() == null) {
			DbAdapterImpl.init(getInstrumentation().getContext());
		}
		
		Track t = Track.createRemoteTrack(UUID.randomUUID().toString());
		
		t.setMeasurementsAsArrayList(createMeasurements(t), true);
		DbAdapterImpl.instance().insertTrack(t, true);
		
		Track dbTrack = DbAdapterImpl.instance().getTrack(t.getTrackId(), true);
		
		Assert.assertTrue("Track is not marked as lazy!", dbTrack.isLazyLoadingMeasurements());
		
		try {
			dbTrack.getStartTime();
			dbTrack.getEndTime();
		} catch (MeasurementsException e) {
			Assert.fail(e.getMessage());
		}
		
		Assert.assertTrue("Expected 10 measurements!", dbTrack.getMeasurements().size() == COUNT);
		
		DbAdapterImpl.instance().deleteTrack(dbTrack.getTrackId());
		
		Assert.assertTrue("Expected an empty list", DbAdapterImpl.instance().getAllMeasurementsForTrack(dbTrack).isEmpty());
		
		Assert.assertTrue("Track was expected to be deleted!", !DbAdapterImpl.instance().hasTrack(dbTrack.getTrackId()));
	}

	private List<Measurement> createMeasurements(Track t) throws InterruptedException {
		List<Measurement> result = new ArrayList<Measurement>();
		
		for (int i = 0; i < COUNT; i++) {
			result.add(createRandomMeasurement(t));	
		}
		
		return result;
	}

	private Measurement createRandomMeasurement(Track t) throws InterruptedException {
		Measurement result = new Measurement(51.0f + (random.nextDouble()/100f), 57.0f + (random.nextDouble()/100f));
		result.setTime(System.currentTimeMillis());
		result.setTrackId(t.getTrackId());
		Thread.sleep(10);
		return result;
	}

}
