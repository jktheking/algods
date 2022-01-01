package edu.algo.algointro;

import java.time.Duration;
import java.util.BitSet;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

	public static void main(String[] args) {
		
		int size = getBirthdayParadoxSafeLimit(9);
		long startTime =  System.nanoTime();
		for(int i=0; i< 10000000; i++) {
		ThreadLocalRandom.current().ints(size, 1, 10).distinct().limit(9);
		}
		long endTime  = System.nanoTime();
		System.out.println("with birthDayparadox:"+ Duration.ofNanos(endTime - startTime).toMillis());
		
		
		 startTime =  System.nanoTime();
		for(int i=0; i< 10000000; i++) {
		ThreadLocalRandom.current().ints(1, 10).distinct().limit(9);
		}
		 endTime  = System.nanoTime();
		System.out.println("without birthDayparadox:"+ Duration.ofNanos(endTime - startTime).toMillis());
	}
	
	
	/**
	 * 
	 * 
	 * */
	public static int getAnyValueInRange1(int minInclusive, int maxExclusive) {
		// Math.random generates inclusive values between 0.0 to 0.9999..
		// to avoid 0, when minInclusive is non-zero we need to add minInclusive
		return (int) (Math.random() * (maxExclusive - minInclusive)) + minInclusive;
	}

	public static int getAnyValueInRange2(int minInclusive, int maxExclusive) {
		return ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive);
	}
	
	
	/***
	 * 
	 * 1. BitSet based method 'getDistinctIntegers3' is the fastest one.
	 * 
	 * Theoritically IntStream with streamSize given should be faster than the IntStream without streamSize.
	 * but practically this is not happening.. Why ? .. need to figrueout...
	 * 
	 * 
	 * 
	 * */
	public static int[] getDistinctIntegers(int minInclusive, int maxExclusive, int limit) {
		return ThreadLocalRandom.current().ints(minInclusive, maxExclusive).distinct().limit(limit).toArray();
	}

	
	
	public static int[] getDistinctIntegers1(int minInclusive, int maxExclusive, int limit) {
		return ThreadLocalRandom.current().ints(getBirthdayParadoxSafeLimit(limit), minInclusive, maxExclusive).distinct().limit(limit).toArray();
	}
	
	/**
	 * This is not thread safe
	 * */
	public static int[] getDistinctIntegers2(int minInclusive, int maxExclusive, int limit) {
		return new SplittableRandom().ints(getBirthdayParadoxSafeLimit(limit), minInclusive, maxExclusive).distinct().limit(limit).toArray();
	}

	/**
	 * x = 2^n; repeation will start at 2^(n/2)
	 * 
	 * */
	private static int getBirthdayParadoxSafeLimit(int limit) {
		//returns the nearest next power of 2 of limit.
		int nextPowerOf2 = Integer.highestOneBit(limit) << 1;
		return nextPowerOf2*nextPowerOf2;
	}
	
	public static int[] getDistinctIntegers3(int minInclusive, int maxExclusive, int limit) {

		BitSet distinctSet = new BitSet(maxExclusive);

		int[] randomHolder = new int[limit];

		for(int i=0; i< limit; i++) {

			while(true) {
				int randomVal = ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive);
				if(!distinctSet.get(randomVal)) {
					//mark this randomVal as generated.
					distinctSet.set(randomVal);
					randomHolder[i] = randomVal;
					break;
				}
			}
		}

		return randomHolder;

	}
	
}
