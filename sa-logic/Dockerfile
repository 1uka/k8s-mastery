FROM python:slim

COPY sa /app
WORKDIR /app

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
RUN python -m textblob.download_corpora

EXPOSE 5000

ENTRYPOINT ["python"]

CMD ["sentiment_analysis.py"]
