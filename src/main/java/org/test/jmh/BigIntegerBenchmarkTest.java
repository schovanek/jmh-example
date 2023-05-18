package org.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 2)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BigIntegerBenchmarkTest {
    private long longNum;
    private BigInteger bigNum;
    private BigInteger bigNum2;

    @Setup
    public void setup() {
        longNum = 100000L;
        bigNum = BigInteger.valueOf(longNum);
        bigNum2 = new BigInteger("100000010000001000000");
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void toBig(Blackhole blackhole) {
        blackhole.consume(BigInteger.valueOf(longNum++));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void bigAdd(Blackhole blackhole) {
        blackhole.consume(bigNum.add(BigInteger.ONE));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void bigPow(Blackhole blackhole) {
        blackhole.consume(bigNum.pow(5));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void bigPow2(Blackhole blackhole) {
        blackhole.consume(bigNum2.pow(5));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void bigMultiply(Blackhole blackhole) {
        BigInteger a2 = BigInteger.valueOf(longNum * longNum);
        BigInteger a3 = BigInteger.valueOf(longNum * longNum * longNum++);
        blackhole.consume(a3.multiply(a2));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void aLongPlus(Blackhole blackhole) {

        blackhole.consume(longNum++);
    }
}