package com.github.rglv.uuid.jmh;

import com.github.rglv.uuid.UUIDs;
import java.util.UUID;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class UUIDsJmhTests {
    @State(Scope.Benchmark)
    public static class TestState {
        public static UUID[] arr = new UUID[1024];
        public static String[] strings = new String[1024];

        static {
            for (int i = 0; i < 1024; i++) {
                arr[i] = UUID.randomUUID();
                strings[i] = arr[i].toString();
            }
        }
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public void utilFromString(TestState state) {
        for (String s: state.strings) {
            UUIDs.fromString(s);
        }
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public void originalFromString(TestState state) {
        for (String s: state.strings) {
            UUID.fromString(s);
        }
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public void utilToString(TestState state) {
        for (UUID uuid : state.arr) {
            UUIDs.toString(uuid);
        }
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public void originalToString(TestState state) {
        for (UUID uuid : state.arr) {
            uuid.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
