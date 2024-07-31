def is_cycle(node, vis, rec_stack, st, adj):
    vis[node] = True
    rec_stack[node] = True
    st.append(node)

    for neighbor in adj[node]:
        if not vis[neighbor]:
            if is_cycle(neighbor, vis, rec_stack, st, adj):
                return True
        elif rec_stack[neighbor]:
            st.append(neighbor)
            return True

    rec_stack[node] = False
    st.pop()
    return False

def extract(st):
    node = st[-1]
    cycle = []
    cycle.append(node)
    st.pop()

    while st:
        top = st[-1]
        cycle.append(top)
        if top == node:
            break
        st.pop()
    
    return cycle

def round_trip(n, adj):
    vis = [False] * (n + 1)
    rec_stack = [False] * (n + 1)
    st = []

    for i in range(1, n + 1):
        if not vis[i]:
            if is_cycle(i, vis, rec_stack, st, adj):
                return extract(st)
    
    return []

def main():
    import sys
    input = sys.stdin.read
    data = list(map(int, input().split()))

    idx = 0
    n = data[idx]
    idx += 1
    m = data[idx]
    idx += 1

    adj = [[] for _ in range(n + 1)]

    for _ in range(m):
        a = data[idx]
        idx += 1
        b = data[idx]
        idx += 1
        adj[a].append(b)

    cycle = round_trip(n, adj)

    if not cycle:
        print("NOT POSSIBLE")
    else:
        cycle.reverse()
        print(len(cycle))
        print(" ".join(map(str, cycle)))

if __name__ == "__main__":
    main()
