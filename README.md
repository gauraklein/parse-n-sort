# Parse-N-Sort

Simple leiningen app that offers an api and cli tool to parse and sort a set of records that are stored in 3 different formats.

## Installation

Navigate to the root folder and run `lein deps`

## Usage

### CLI

The CLI tool displays data from a specified file in 3 output formats.

The available file paths are
```
./resources/pipe.txt
./resourcs/comma.csv
./resources/spaces.txt
```
The available output formats are

```
output 1: sorted by favorite color then last name, ascending
output 2: sorted by birth date, ascending
output 3: sorted by last name, descending
```
The app is invoked by using `lein run` with the first arg being the `filepath` and the second being the `output format`:
```
lein run <filepath> <output-format>
lein run ./resources/pipe.txt 2
```

### API

The API server is initialized by running `lein server` you can post new record data or request all record data sorted in various formats. The server is run on port 4000 and it's endpoints are as follows: 
```sh
GET - "/records/color" - returns records sorted by color
GET - "/records/birthdate" - returns records sorted by birthdate
GET - "/records/name" - returns records sorted by last name
POST - "/records" - creates a line of data within the corrosponding file based on format
```
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/e54bbba82b59a4ca2a5a) 

The above button is a postman collection of these requests.

The `POST` body takes a key of `new-line` and is looking for a string that matches either of the 3 formats the app will accept

Pipes: `last | first | e@mail.com | color | 2000-01-01`

Commas: `last, first, e@mail.com, color, 2000-01-01`

Spaces: `last first e@mail.com color 2000-01-01`

## Testing

To run the test suite enter `lein test`

## Areas to Improve

Currently this app is pretty fragile and doesn't handle much besides the happy path. If I had more time to spend on this the first thing I would do is dig into validation and error handling. There are a lot of areas for improvement and these are the primary ones on my mind:

- Validate data being posted to `/records` (ensure there are 5 values, last value must be a date etc)
- Add an argument for sort direction to the sorting function (this would allow for dynamic sorting)
- Make use of query params to be able to more flexibly request data (direction, sort-value, file)
- Validation and error handling for when a record already exists
- The `sort-records` function is currently sorting and formatting it would be good to separate those concerns

## Takeaways
This was a lot of fun :)
