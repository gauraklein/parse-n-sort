# parse-n-sort

FIXME: description

## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: explanation

    $ java -jar parse-n-sort-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

### TO IMPROVE
- add validation to ensure that each record contains 5 values in parse file (especially for data coming into the post request)
- upgrade the sorting function to take a direction as well
- add a query param for sort direction to the get requests
- add a get for the 'records' endpoint that takes a sort value as a query param
- error handling for when a record already exists
- decouple formatting code from sorting code

--- TODO 
- Add endpoint tests
- update readme
- postman collection of api-requests?
- help command on the cli for arguments?