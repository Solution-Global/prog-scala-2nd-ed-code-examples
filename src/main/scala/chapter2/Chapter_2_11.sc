///////////////////////////////////////
// 2.11 파일과 이름공간으로 코드 구조화하기
///////////////////////////////////////

package com {
  package example {
    package pkg1 {
      class Class11 {
        def m = "m11"
      }
      class Class12 {
        def m = "m12"
      }
    }

    package pkg2 {
      class Class21 {
        def m = "m21"
        def makeClass11 = {
          new pkg1.Class11
        }
        def makeClass12 = {
          new pkg1.Class12
        }
      }
    }

    // 연달아 적용 가능
    package pkg3.pkg31.pkg311 {
      class Class311 {
        def m = "m21"
      }
    }
  }
}
