FORMAT: 1A

# OKB Curation REST API

TODO: Add a description

# Group Votes

TODO: Add a description

## Overview of items with votes [/votes]

### Get items with votes in a specific order [GET]

+ Parameters

    + limit (number, optional) - Maximum number of results to return.
        + Default: `20`
    
    + skip (number, optional) - Number of results to skip before returning.
        + Default: `0`
        
    + order (string, optional) - Results are ordered by item ID, either ascending `asc` or descending `desc`.
        + Default: `asc`
        
+ Response 200 (application/json)

    + Body
    
    
        {
            "items": [array[Item]],
            "next": "/votes?limit=20&skip=20&order=asc"
        }
    

## All votes of an item [/votes/{item_id}]

+ Parameters

    + item_id (number, required) - Wikidata item ID, e.g. 42

### Get votes for all statements in specific item [GET]

+ Response 200 (application/json)

    + Body
    
        [Item]
        
+ Response 404 (application/json)

    + Body
    
    
        {
            "error_code": 404,
            "error_message": "item Q7 does not exist or no statement votes registered for statements in Q7",
            "item_id": 7,
            "url": "/votes/7"
        }

## Votes for a statement group [/votes/{item_id}/{property_id}]

+ Parameters
    + item_id (number, required) - numeric Wikidata item ID, e.g. 42
    + property_id (number, required) - numeric Wikidata property ID, e.g. 31

### Get votes for the statements in the group [GET]

+ Response 200 (application/json)

    + Body
        
        [StatementGroup]
        
### Vote for a statement in the statement group [POST]

+ Request (application/json)
    <!-- TODO
     The specifics of the POST body depend on the implementation (how votes are stored etc.)
    -->

    + Body
    
    
        {
            "statement_id": "Q42$F078E5B3-F9A8-480E-B7AC-D97778CBBEF9",
            "user_id": "Wikimedia user name"
        }

+ Response 204

# Data Structures

<!-- TODO
This data structures are only concerned with the votes endpoint.
Maybe we should rename them appropiatly.
-->

## Item (object)
+ item_id (number, required) - numeric Wikidata item ID, e.g 42 for Q42
+ url (string, required) - url to the current item, e.g. /votes/42
+ statement_groups (array[StatementGroup], required) - list of statement groups with votes

## StatementGroup (object)
+ item_id (number, required) - numeric Wikidata item ID, e.g. 42 for Q42
+ property_id (number, required) - numeric Wikidata property ID, e.g. 31 for P31
+ url (string, required) - url to the current statement group, e.g. /votes/42/31
+ statements (array[Statement], required) - list of statement with votes in this group

## Statement (object)
+ item_id (number, required) - numeric Wikidata item ID, e.g. 42 for Q42
+ property_id (number, required) - numeric Wikidata property ID, e.g. 31 for P31
+ statement_id (string, required) - identifier for the specific statement in Wikidata, e.g. Q42$F078E5B3-F9A8-480E-B7AC-D97778CBBEF9
+ votes (number, required) - number of votes this statement received
