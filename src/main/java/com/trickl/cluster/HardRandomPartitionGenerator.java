/*
 * This file is part of the Trickl Open Source Libraries.
 *
 * Trickl Open Source Libraries - http://open.trickl.com/
 *
 * Copyright (C) 2011 Tim Gee.
 *
 * Trickl Open Source Libraries are free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Trickl Open Source Libraries are distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this project.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.trickl.cluster;

import cern.colt.matrix.DoubleMatrix2D;
import cern.jet.random.Uniform;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.RandomEngine;

public class HardRandomPartitionGenerator implements PartitionGenerator {

   private RandomEngine randomEngine;

   public HardRandomPartitionGenerator() {
      randomEngine = new MersenneTwister();
   }

   @Override
   public void generate(DoubleMatrix2D partition) {
      // Initialise U randomly
      partition.assign(0);

      Uniform uniform = new Uniform(randomEngine);

      for (int i = 0; i < partition.rows(); ++i)
      {
         // Randomise
         int k = uniform.nextIntFromTo(0, partition.columns() - 1);
         partition.setQuick(i, k, 1);
      }
   }

   public RandomEngine getRandomEngine() {
      return randomEngine;
   }

   @Override
   public void setRandomEngine(RandomEngine random) {
      this.randomEngine = random;
   }
}
