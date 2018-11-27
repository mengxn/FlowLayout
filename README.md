# FlowLayout
FlowLayout is an layout that display its children in multiple rows depending on their size  
## Example
![](device-2016-07-03-222937.png)
## Usage
### Gradle
```
compile 'me.codego.view:flowlayout:1.0.2'
```
### Maven
```
<dependency>
  <groupId>me.codego.view</groupId>
  <artifactId>flowlayout</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```
### Layout
```
<me.codego.view.FlowLayout
    android:id="@+id/flow_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:horizontalSpacing="10dp"
    app:verticalSpacing="15dp" />
```
> horizontalSpacing 水平间距  
> verticalSpacing 垂直间距

### Code
``` java
for (String tag : tags) {
    flowLayout.addView(initTag(tag));
}
```

