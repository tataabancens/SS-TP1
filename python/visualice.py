from turtle import color
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

df = pd.read_csv('positions.csv')

x = df['x'].tolist()
y = df['y'].tolist()
ids = df['id'].tolist()

# id = int(input("Select a point: "))

myId = 0

dfNeighbours = pd.read_json('neighbours.json')

point = dfNeighbours.iloc[myId]
dfNeighbours = dfNeighbours['neighbours']
neighbours = dfNeighbours.to_dict()

fig, ax = plt.subplots()

ax.scatter(x, y)
ax.scatter(x[myId], y[myId], color='g')

xneigh = []
yneigh = []

position_dict = df.to_dict()

for id in ids:
    if (id in neighbours[myId]):
        xneigh.append(x[id])
        yneigh.append(y[id])

ax.scatter(xneigh, yneigh, color='r')
ax.grid()
plt.show()