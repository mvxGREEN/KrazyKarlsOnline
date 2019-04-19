# KrazyKarlsOnline
**Android client for Krazy Karl's Pizza**
_ By Mvx Green _

### SCREENSHOTS:

![alt text](https://raw.githubusercontent.com/mvxGREEN/KrazyKarlsOnline/master/screenshots/KKO_HOME_2.jpg)


### GOALS:

- Online ordering & billing system (pickup or delivery)
  - Customization: user should be able to add/remove/sub ingredients from all items, and include other preferences such as cook time (reg. or crispy), thin or gluten-free crust, number of slices, slice shape (reg. or square), etc.
- Detailed menu info
  - Photos and/or generated graphics
  - Highlight common allergens
  - Highlight vegetarian, vegan, dairy-free, and gluten-free options
- Friendly and efficient UI
- Interactive map (Google Maps and Google Places
  APIs)
  - Delivery zones, store locations (fixed)
  - Store info: wait time, hours, address, phone #, upcoming events...
  - Custom location markers & current location button (dynamic)



### TODO:
  - Register OnClickListeners for locations (markers & buttons)
  - Create **LocationDetails** class & layout; inflate when location is clicked (button or marker)
    - Bind store data; incl. images from Google Places
    - 'Order Now' button
  - Create **ItemDetails** class & layout; inflate when item is clicked
    - Bind detailed item data
    - Customize toppings
    - 'Add to cart' button



### KNOWN BUGS:
  - **FragMenu** header & content sections don't respond when trying to swipe left (should scroll to next frag)
  - When scrolling to **FragMenu** from **FragOrder**,
