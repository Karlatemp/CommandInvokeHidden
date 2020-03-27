# CommandInvokeHidden

-----
这是一个API, 使用下面的代码来隐藏执行的命令.

This is API. Use the code in bottom to hidden invoked command.

---

```java
public class PluginMain extends JavaPlugin{
    public void onEnable(){
        Bukkit.getPluginManager().regsterEvents(new Listener(){
            @EventHandler
            public void on(PlayerInvokeCommandLoggingEvent event) {
                if(event.getCommand().startsWith("/help"))
                    event.setCancelled(true);
            }
        }, this);
    }
}
```