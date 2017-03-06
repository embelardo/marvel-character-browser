# marvel-characters-web-app

A Spring MVC web application that allows a user to search for characters from the Marvel Universe and get detailed information about them.

### implementation

This web application acquires all its information from the [Marvel API](http://developer.marvel.com/docs). Unfortunately, most character details are buried in a wiki page, so character data is aggregated by parsing the json data returned by the Marvel API and scraping the character's corresponding wiki page.
