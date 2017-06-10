val name = "Buck Trends"
println(s"Hello, $name")

val gross = 100000F
val net = 64000F
val percent = (net / gross) * 100
println(f"$$${gross}%.2f vs. $$${net}%.2f or ${percent}%.1f%%")

// int -> float, double 은 가능
val i = 200
f"${i}%.2f"

// 반대로는 불가능
//val d = 100.22
//f"${d}%2d"

// format 사용
val s = "%02d: name = %s".format(5, "Dean Wampler")

// escape
val name2 = "Dean Wampler"
s"123\n$name2\n456"

// escape 하지 않고 그대로 보여주기
raw"123\n$name2\n456"