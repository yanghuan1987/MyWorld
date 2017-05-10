class person:
    name = ''
    age = 0
    __sex = 'man'

    def __init__(self, na, ag, se):
        self.name = na
        self.age = ag
        self.__sex = se

    def speak(self):
        print("%s say:I am %d years old." % (self.name, self.age))


p = person('Scott', 18, 'man')
p.speak()
