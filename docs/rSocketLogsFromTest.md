    024-08-21 16:14:32.463 DEBUG io.rsocket.FrameLogger - sending ->
Frame => Stream ID: 0 Type: SETUP Flags: 0b100000000 Length: 138
Metadata:
+-------------------------------------------------+
|  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| 22 6d 65 73 73 61 67 65 2f 78 2e 72 73 6f 63 6b |"message/x.rsock|
|00000010| 65 74 2e 61 75 74 68 65 6e 74 69 63 61 74 69 6f |et.authenticatio|
|00000020| 6e 2e 76 30 00 00 15 80 00 03 66 6f 6f 73 74 75 |n.v0......foostu|
|00000030| 62 2d 75 73 65 72 2d 74 6f 6b 65 6e             |b-user-token    |
+--------+-------------------------------------------------+----------------+
Data:

2024-08-21 16:14:32.481 DEBUG io.rsocket.FrameLogger - sending ->
Frame => Stream ID: 1 Type: REQUEST_STREAM Flags: 0b100000000 Length: 37 InitialRequestN: 64
Metadata:
+-------------------------------------------------+
|  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| fe 00 00 14 13 61 70 69 2e 76 31 2e 6e 6f 74 69 |.....api.v1.noti|
|00000010| 66 69 63 61 74 69 6f 6e                         |fication        |
+--------+-------------------------------------------------+----------------+
Data:

2024-08-21 16:14:32.483 DEBUG io.rsocket.FrameLogger - receiving ->
Frame => Stream ID: 0 Type: SETUP Flags: 0b100000000 Length: 138
Metadata:
+-------------------------------------------------+
|  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| 22 6d 65 73 73 61 67 65 2f 78 2e 72 73 6f 63 6b |"message/x.rsock|
|00000010| 65 74 2e 61 75 74 68 65 6e 74 69 63 61 74 69 6f |et.authenticatio|
|00000020| 6e 2e 76 30 00 00 15 80 00 03 66 6f 6f 73 74 75 |n.v0......foostu|
|00000030| 62 2d 75 73 65 72 2d 74 6f 6b 65 6e             |b-user-token    |
+--------+-------------------------------------------------+----------------+
Data:

2024-08-21 16:14:32.779 DEBUG io.rsocket.FrameLogger - receiving ->
Frame => Stream ID: 1 Type: REQUEST_STREAM Flags: 0b100000000 Length: 37 InitialRequestN: 64
Metadata:
+-------------------------------------------------+
|  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| fe 00 00 14 13 61 70 69 2e 76 31 2e 6e 6f 74 69 |.....api.v1.noti|
|00000010| 66 69 63 61 74 69 6f 6e                         |fication        |
+--------+-------------------------------------------------+----------------+
Data:

2024-08-21 16:14:32.859 DEBUG io.rsocket.FrameLogger - sending ->
Frame => Stream ID: 1 Type: NEXT Flags: 0b100000 Length: 65
Data:
+-------------------------------------------------+
|  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| 7b 22 69 64 22 3a 22 31 32 33 65 34 35 36 37 2d |{"id":"123e4567-|
|00000010| 65 38 39 62 2d 31 32 64 33 2d 61 34 35 36 2d 34 |e89b-12d3-a456-4|
|00000020| 32 36 36 31 34 31 37 34 30 30 30 22 2c 22 62 6f |26614174000","bo|
|00000030| 64 79 22 3a 22 74 65 73 74 22 7d                |dy":"test"}     |
+--------+-------------------------------------------------+----------------+
2024-08-21 16:14:32.860 DEBUG io.rsocket.FrameLogger - receiving ->
Frame => Stream ID: 1 Type: NEXT Flags: 0b100000 Length: 65
Data:
+-------------------------------------------------+
|  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| 7b 22 69 64 22 3a 22 31 32 33 65 34 35 36 37 2d |{"id":"123e4567-|
|00000010| 65 38 39 62 2d 31 32 64 33 2d 61 34 35 36 2d 34 |e89b-12d3-a456-4|
|00000020| 32 36 36 31 34 31 37 34 30 30 30 22 2c 22 62 6f |26614174000","bo|
|00000030| 64 79 22 3a 22 74 65 73 74 22 7d                |dy":"test"}     |
+--------+-------------------------------------------------+----------------+