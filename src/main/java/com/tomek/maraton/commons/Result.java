package com.tomek.maraton.commons;

import com.tomek.maraton.commons.events.MaratonEvent;
import io.vavr.control.Either;
import lombok.Value;

import java.util.List;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;
import static java.util.Collections.emptyList;


public class Result {

    final Either<Failure, Success> result;

    public static Result success(MaratonEvent... events) {
        return new Result(right(new Success(List.of(events))));
    }

    public static Result failure(String reason) {
        return new Result(left(new Failure(reason)));
    }

    private Result(Either<Failure, Success> result) {
        this.result = result;
    }

    public boolean isFailure() {
        return result.isLeft();
    }

    public boolean isSuccess() {
        return result.isRight();
    }

    public String reason() {
        if (result.isLeft()) {
            return result.getLeft().getReason();
        }
        return "";
    }

    public List<MaratonEvent> events() {
        return result
                .map(Success::getEvents)
                .getOrElse(emptyList());
    }
}

@Value
class Success {
    List<MaratonEvent> events;
}

@Value
class Failure {
    String reason;
}
