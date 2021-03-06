/**
 *    Copyright 2016-today GDG Toledo ES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.gdg.toledo.katangapp.models;

import es.gdg.toledo.katangapp.business.UnreferenceablePointException;

import java.io.Serializable;

/**
 * This class represent the segment between two points.
 *
 * In terms of the application, it will represent the segment between user's
 * current location and any bus stop's location, calculating the distance
 * between those two point on its constructor.
 *
 * @author mdelapenya
 *
 * @see ReferenceablePoint
 */
public class Segment implements Comparable<Segment>, Serializable {

	/**
	 * Creates the segment object defined by the two points passed in as
	 * arguments.
	 *
	 * @param from Start point of the segment
	 * @param to End point of the segment
	 *
	 * @throws UnreferenceablePointException when any point is not referenced or
	 *                                       is null.
	 */
	public Segment(ReferenceablePoint from, ReferenceablePoint to)
		throws UnreferenceablePointException {

		if (from == null) {
			throw new UnreferenceablePointException();
		}

		this.from = from;
		this.to = to;
		this.distance = from.distanceTo(to);
	}

	@Override
	public int compareTo(Segment that) {
		if (distance < that.distance) {
			return -1;
		}
		else if (distance > that.getDistance()) {
			return 1;
		}

		return 0;
	}

	@Override
	public boolean equals(final Object that) {
		if (this == that) {
			return true;
		}

		if (!(that instanceof Segment)) {
			return false;
		}

		Segment segmentThat = (Segment)that;

		return((distance == segmentThat.distance) &&
			(from.equals(segmentThat.from)) && (to.equals(segmentThat.to)));
	}

	public double getDistance() {
		return distance;
	}

	public ReferenceablePoint getFrom() {
		return from;
	}

	public ReferenceablePoint getTo() {
		return to;
	}

	@Override
	public int hashCode() {
		int hashCode = 31;

		long l = Double.doubleToLongBits(distance);

		int c = (int) (l ^ (l >>> 32));

		hashCode = 37 * hashCode + c;

		c = from.hashCode();

		hashCode = 37 * hashCode + c;

		c = to.hashCode();

		hashCode = 37 * hashCode + c;

		return hashCode;
	}

	@Override
	public String toString() {
		return "from: [" + from + "], to: [" + to + "], distance: " + distance;
	}

	private final double distance;
	private final ReferenceablePoint from;
	private final ReferenceablePoint to;

}
