package main
import ("fmt"; "math")

/*
Golang: It is an open source programming language that makes it easy to build simple, reliable, and efficient software.

- To run go file: go run filename.go
- var: To declare a variable; const: To declare a constant/ unchangable value.
- Its an error if you declare a variable and do not use it.
- In functions, the type comes after the variable name.
- FunctionName with capital letter means it is public (accessible from other packages).

*/

func calc(a int, b int) (int, int) {
	var res1= a + b
	var res2= a * b
	return res1, res2
}

func main() {
	var num=3	// num := 3
	const pi=math.Pi	// constant declaration

	for i:=1; i<=5; i++{	// for loop
		fmt.Println("Hello World!", num, pi)
	}

	calcRes1, calcRes2 := calc(2, 3)	// Function call
	fmt.Println("Calc Results: ", calcRes1, calcRes2)

	// if else condition
	if num > 0 {	fmt.Println("Num is positive")
	} else {	fmt.Println("Num is non-positive") }

	switch num {	// switch case
		case 1:
			fmt.Println("Num is one")
		case 2:
			fmt.Println("Num is two")
		default:
			fmt.Println("Num is neither one nor two")
	}

	var arr = [3]int{13, 27, 93}	// array declaration
	for i:=0; i<len(arr); i++ {		fmt.Println("Array element: ", arr[i]) }

	// map declaration
	var myMap = make(map[string]int)
	myMap["Alice"] = 25;	myMap["Bob"] = 30
	for key := range myMap {	fmt.Println("Map element: ", key, myMap[key]) }
}
