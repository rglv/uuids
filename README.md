# uuids
Slightly better UUID to String conversions than JDK8.

| Benchmark                                | Mode  | Cnt | Score      | Error     | Units |
|------------------------------------------|-------|-----|------------|-----------|-------|
| UUIDsComparativeTests.originalFromString | thrpt | 40  | 1010,408   |  ±6,812   | ops/s |
| UUIDsComparativeTests.utilFromString     | thrpt | 40  | 3010,289   | ± 152,654 | ops/s |
| UUIDsComparativeTests.originalToString   | thrpt | 40  | 1892,812   | ± 15,124  | ops/s |
| UUIDsComparativeTests.utilToString       | thrpt | 40  | 4312,794   | ± 39,431  | ops/s |
