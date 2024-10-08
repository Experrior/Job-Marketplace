FROM python:alpine3.20

RUN apk add libpq-dev python3-dev gcc musl-dev libc-dev make

COPY services/db_mockup/py_init/mockdata.json .

COPY services/db_mockup/py_init/requirements.txt .

RUN python3 -m pip install -r requirements.txt

CMD python3 -m db_mockdata.main -f mockdata.json