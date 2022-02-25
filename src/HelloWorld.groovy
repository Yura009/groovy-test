class HelloWorld {
    static void main(String[] args) {
        println "Hello World"

        int age = 40
        println age
        println age.getClass()

        def name = "John"
        println name
        println name.getClass()
        println "------------------------------------------------------"
        Person person = new Person()

        person.setFirstName("Yura")
        person.setLastName("Morozyuk")
        person.setAge(20)

        println person.getFullName()
        println person.getAge()
        println "------------------------------------------------------"

        def persons = [person, new Person(firstName: "Vika", lastName: "Hrynchuk", age: 19)]

        for (Person p : persons) {
            println p.getFullName()
        }
        println "------------------------------------------------------"
        try {
            person.getFullName().toLong()
        } catch (Exception e) {
            assert e instanceof NumberFormatException
            println "Cannot"
        }
        println "------------------------------------------------------"
        println person.getFirstName().dropRight(2)
        println "------------------------------------------------------"
        println Calculator.add(5, 6)
        try {
            println Calculator.divide(7, 0)
        } catch (RuntimeException e) {
            println "Exception catch"
        }
        println Calculator.multiply(1, 0)
        println Calculator.subtract(7, 10)
        println "------------------------------------------------------"
        Closure personToString = { Person person1 -> println person1.toString() }
        handlePerson(personToString, person)
        println "------------------------------------------------------"
        assert persons instanceof List
        assert persons.size() == 2
        assert persons[0] == person
        persons.each { println it }
        persons.eachWithIndex { Person entry, int i -> println i + ":" + person }
        println persons.find { it.lastName == 'Morozyuk' } == person
        println persons.collect { it.age <= 30 } == [true, true]
        println persons.sort { it.age } == [person, new Person(firstName: "Vika", lastName: "Hrynchuk", age: 19)]
        println "------------------------------------------------------"
        Person davaDavidov = new Person()
        File file = new File("resources/dava-davidov")
        println file.getText("UTF-8")
        file.eachLine { line, no ->
            if (no == 1) {
                davaDavidov.setFirstName(line)
            } else if (no == 2) {
                davaDavidov.setLastName(line)
            } else if (no == 3) {
                davaDavidov.setAge(line.toInteger())
            } else {
                throw new RuntimeException("A person text file should only have 3 lines")
            }
        }
        println davaDavidov
        println "------------------------------------------------------"
        Person ihorValish = new Person("Ihor", "Valish", 20)
        File textFile = new File("resources/ihor-valish.txt")
        textFile.withWriter('UTF-8') { writer ->
            writer.writeLine("Ihor")
            writer.writeLine("Valish")
            writer.writeLine("30")
        }


        textFile.append("1")
        textFile << "2"
        println "------------------------------------------------------"
        List<Integer> integerList = readAllNumbers()
        println integerList

        Integer max = integerList.max()
        println max

        Integer listInt = integerList.sum()
        println listInt
    }


    private static List<Integer> readAllNumbers() {
        File resourcesDir = new File("resources")
        List<Integer> allNumbers = []

        resourcesDir.eachFile { file ->
            file.eachLine { line ->
                if (line.isNumber()) {
                    allNumbers << line.toInteger()
                }
            }
        }
        return allNumbers
    }

    static void handlePerson(Closure c, Person p) {
        if (p == null) {
            throw new RuntimeException("Cannot")
        }
        c(p)
    }
}




