# KrazyKarlsOnline
**Android client for Krazy Karl's Pizza**
_By Mvx Green_

### Screenshots
![alt text](https://raw.githubusercontent.com/mvxGREEN/KrazyKarlsOnline/master/screenshots/KKO_MENU_2.jpg)

### Mission
To extend the online presence of Krazy Karl's Pizza with an Android client emphasizing user-first, mobile-friendly design.

### Active Goals
- Online ordering & billing system (pickup or delivery)
  - Customization: user should be able to add/remove/sub ingredients from all items, and include other preferences such as cook time (reg. or crispy), thin or gluten-free crust, number of slices, slice shape (reg. or square), etc.
- Menu info:
  ~~Item names, toppings, and available sizes~~
  - Photos and/or generated graphics
  - Highlight common allergens
  - Highlight vegetarian, vegan, dairy-free, and gluten-free options
- Friendly and efficient UI
- Interactive map
  ~~Delivery zones, store locations~~
  - Store info: wait time, hours, address, phone #, upcoming events...
  ~~Custom location markers & current location button~~



### TODO:
  - ~~Register OnClickListeners for locations (markers & buttons)~~
  - Create **LocationDetails** class & layout; inflate when location is clicked (button or marker)
    - Bind store data; incl. images from Google Places
    - 'Order Now' button
  - Create **ItemDetails** class & layout; inflate when item is clicked
    - Bind detailed item data
    - Customize toppings
    - 'Add to cart' button



### KNOWN BUGS:
  - **FragMenu** header & content sections don't respond when trying to swipe left (should scroll to next frag)
  - When scrolling to **FragMenu** from **FragOrder**, error occurs when trying to inflate FragMenu on top of old instance.
