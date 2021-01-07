# Ubuntu Command .Zip

- access log
```shell
> last
```

- attack log
```shell
> journalctl -f
```

- Blocked Access Ip List By sshGuard
```shell
> sudo iptables -nvL sshguard

Chain sshguard (0 references)
 pkts bytes target     prot opt in     out     source               destination
    0     0 DROP       all  --  *      *       112.85.42.200        0.0.0.0/0
    0     0 DROP       all  --  *      *       221.181.185.141      0.0.0.0/0
    0     0 DROP       all  --  *      *       182.53.10.82         0.0.0.0/0
    0     0 DROP       all  --  *      *       112.85.42.98         0.0.0.0/0
    0     0 DROP       all  --  *      *       218.92.0.198         0.0.0.0/0
```