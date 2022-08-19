import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

df = pd.read_csv('test_results.csv')

plt.style.use('_mpl-gallery')

# make data
N = df['N'].tolist()
times = df['time'].tolist()

# plot
fig, ax = plt.subplots()

ax.scatter(N, times, linewidth=2.0)


plt.show()