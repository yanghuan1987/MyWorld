import math
import sys
from decimal import *



def chudnovsky(n):
    pi = Decimal(0)
    k = 0
    factorial = 1
    while k < n:
        pi += (Decimal(-1)**k)*(Decimal(factorial(6*k))/((factorial(k)**3)*(factorial(3*k))) * (13591409+545140134*k)/(640320**(3*k)))
        k += 1
    pi = pi*Decimal(10005).sqrt()/4270934400
    pi = pi**(-1)
    return pi


pi = chudnovsky(10)

print(pi)
