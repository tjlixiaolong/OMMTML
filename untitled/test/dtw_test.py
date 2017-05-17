def dtw():
     X=[(1,1),(2,2),(3,3),(4,4),(5,5)]
     Y=[(2,1),(3,2),(4,7),(5,4),(6,5)]
     M=[[distance(X[i],Y[j]) for i in range(len(X))] for j in range(len(Y))]
     l1=len(X)
     l2=len(Y)
     D=[[0 for i in range(l1+1)] for i in range(l2+1)]
     D[0][0]=0
     for i in range(1,l1+1):
         D[0][i]=sys.maxint
     for j in range(1,l2+1):
         D[j][0]=sys.maxint
     for j in range(1,l2+1):
         for i in range(1,l1+1):
             D[j][i]=M[j-1][i-1]+Min(D[j-1][i],D[j][i-1],D[j-1][i-1]+M[j-1][i-1]);

print dtw.__doc__
