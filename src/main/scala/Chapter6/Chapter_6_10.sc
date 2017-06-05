// 6.10 콤비네이터: 가장 뛰어난 소프트웨어 컴포넌트 추상화

// 콤비네이터는 함수와 컬렉션 등 다른 식을 받아서 적절한 작업을 해주는 조합 장치(함수) 로 가장 작은 기본 단위
// map, foreach, zip, filter, fold, reduce
// 이점 : 상대적으로 적의 양의 코드를 사용하여 단순하지 않은 동작을 만들어낼수 있다.
//        데이터를 특정 문제를 위해 구현해야 할 필요가 있는 동작으로부터 분리할 수 있다.

case class Employee (
                    name: String,
                    title: String,
                    annualSalary: Double,
                    taxRate: Double,
                    insurancePremiumPerWeek: Double
                    )
val employees = List(
    Employee("Buck Trends", "CEO", 200000, 0.25, 100.0),
    Employee("Cindy Banks", "CFO", 170000, 0.22, 120.0),
    Employee("Joe Coder", "Developer", 130000, 0.20, 120.0)
)

// 주간 급여를 계산한다.
val netPay = employees map { e =>
    val net = (1.0 - e.taxRate) * (e.annualSalary / 52.0) - e.insurancePremiumPerWeek
    (e, net)
}

// 급여표를 출력한다.
println("** Paychecks:")
netPay foreach {
    case (e, net) => println(f" ${e.name+':'}%-16s ${net}%10.2f")
}

// 보고서를 작성한다.
val report = (netPay foldLeft (0.0, 0.0, 0.0)) {
    case ((totalSalary, totalNet, totalInsurance), (e, net)) =>
        (totalSalary + e.annualSalary/52.0,
            totalNet + net,
            totalInsurance + e.insurancePremiumPerWeek)
}

println("\n** Report:")
println(f"  Total Salary:    ${report._1}%10.2f")
println(f"  Total Net:       ${report._2}%10.2f")
println(f"  Total Insurance: ${report._3}%10.2f")

// employee는 거의 기능이 없다. 최소한의 동작만 들어 있다. (관심사의 분리)