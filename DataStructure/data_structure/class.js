class Student {
  constructor(firstName, lastName, year) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.year = year
  }

  getFullName() {
    return `full name is ${this.firstName[0].toUpperCase() + this.firstName.substr(1).toLowerCase()} ${this.lastName[0].toUpperCase() + this.lastName.substr(1).toLowerCase()}`
  }
  getYear() {
    return this.year
  }
  addYear() {
    this.year += 1
  }
  static EnrollStudents() {
    return 'Enrolling Student'
  }
}

let firstStudent = new Student('Dong', 'Cha', 1997)

console.log(Student.EnrollStudents())

