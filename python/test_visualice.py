import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

df = pd.read_csv('test_results.csv')

plt.style.use('_mpl-gallery')

# make data
N = df['N'].tolist()
cellIndexTimes = df['CellIndexTime(ms)'].tolist()
bruteForceTimes = df['BruteForceTime(ms)'].tolist()

# plot
fig, ax = plt.subplots()

plt.ylabel("time(ms)")
plt.xlabel("Particles")

ax.plot(N, cellIndexTimes, label='cell index', linewidth=2.0,color = 'g')
ax.plot(N,bruteForceTimes, label='brute force', linewidth=2.0,color = 'r')
plt.legend()

plt.show()