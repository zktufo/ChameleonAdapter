# ChameleonAdapter
![img](http://img.ivsky.com/img/tupian/pre/201610/31/bianselong_texie-004.jpg)
An easy way to create multiple item type list using annotation

In daily work, it's hard to manage the adapter with various item type so that we must add lots of code to control the behavior. ChameleonAdapter allows us to create item controller for specific item type using annotation and register the controller into adapter.

## Getting started

### Step 1
Bind the itemViewBinder with the Activity

```
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder; //create the unbinder

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ChameleonAdapter.bind(this); //bind the itemViewBinder with the Activity
        
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();  //unbind the itemViewBInder
    }
```

### Step 2
Define the DefaultItemBinder using annotation (inject the item entity&layout)

```
@BindItem(value = FirstItemEntity.class, layout = R.layout.item_first_layout)
    DefaultItemBinder mFirstItemBinder;
    
@BindItem(value = SecondItemEntity.class, layout = R.layout.item_second_layout)
    DefaultItemBinder mSecondItemBinder;
```

### Step 3
Create the ChameleonAdapter and link the itemViewBinder with the Adapter. Finally set the adapter for RecyclerView

```
ChameleonAdapter mAdapter;
List<BaseEntity> mDatas = new ArrayList();
    
    ..................
 mAdapter = new ChameleonAdapter(this);
        mAdapter.setItems(mDatas);
        mAdapter.link(mFirstItemBinder);
        mAdapter.link(new SecondItemViewBinder(R.layout.item_second_layout));
        mAdapter.addHeaderView(textView);
        homepageList.setAdapter(mAdapter);
```
### Step 4

```
mFirstItemBinder.setOnBindListener(new BaseItemBinder.OnBindListener() {
            @Override
            public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item) {
                TextView txtFirst = (TextView) holder.getViewById(R.id.txt_first);
                txtFirst.setText("haha" + position);

            }
        });
```

### Other usage
You can also create the itemViewBinder by yourself. Just extend the `BaseItemBinder` class.

```
public class FirstItemViewBinder extends BaseItemBinder {
    int layoutId;
    public FirstItemViewBinder(int layoutId) {
        this.layoutId = layoutId;
    }
    @Override
    public RecyclerViewBaseViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerViewBaseViewHolder(inflater.inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseViewHolder holder, int position, Object item) {
    }

    @Override
    public Class getItemClass() {
        return FirstItemEntity.class;
    }
}
``` 

## TODO
- upload to jcenter
- support one-many mode

## Reference
- type management is inspired by [drakeet/MultiType](https://github.com/drakeet/MultiType)
- class loading is inspired by [JakeWharton/butterknife](https://github.com/JakeWharton/butterknife)




