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

ax.plot(N, cellIndexTimes, linewidth=2.0,color = 'g')
ax.plot(N,bruteForceTimes, linewidth=2.0,color = 'r')


plt.show()