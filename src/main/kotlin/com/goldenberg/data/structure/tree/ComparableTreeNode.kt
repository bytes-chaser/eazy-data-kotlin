package com.goldenberg.data.structure.tree

interface ComparableTreeNode<T : Comparable<T>, N : ComparableTreeNode<T, N>> : TreeNode<T, N>
