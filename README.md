Dynamic-UI-Android
==================

Dynamic UI Android is a Demo app in android whose UI can be controlled from the server over webservice calls.

Usage
-----

A hard-coded json return over a web service call:
```json
 [
   {
     "cardType": "type01",
     "cardData": {

     }
   },
   {
     "cardType": "type02",
     "cardData": {

     }
   }
 ]
```

As per the existing example, it inherits only two types of card. For more of them, add the respective models and views as per your need and implement the card specific behaviour on its focus.

Implement your own adapter for the card stack. The CardStack will accept ArrayAdapter.

```java
public class CardsDataAdapter extends ArrayAdapter<String> {

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){

        CardModel object = getItem(position);
        switch (object.type) {
          ...
        }
    }

}
```
