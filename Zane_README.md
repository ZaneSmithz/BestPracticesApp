# Moneybox Technical Task
Hi, assuming a lot of this will be discussed in the technical, but in case you notice some glaring issues
just wanted to clarify a few things

## API calls
- I've just mocked the API call currently as I'm just receiving a 400 each time I attempt to call
- unsure if this is due to incorrectly passing the POST request via header?

## Shared Prefs
- I'm using encrypted shared prefs to obfuscate the token, i'm not clearing it from the cache on destroy
- But ^ this would be something i would've like to implement, but also the requirements don't specify

## Tests
- I wrote tests for the classes i felt most important that demonstrated my ability to write tests
- There are some classes missing tests, but this is more due to lack of time
- these classes are: BalanceUseCase, LoginRequestUseCase, BalanceViewModel
- Would've like to implement some snapshot tests too!

## Weird Dispatcher use?
- I'm using a Main dispatcher on a network call because of navigation callback in LoginViewModel
- ^ i definitely do not advocate this but for the sake of this exercise I think it's okay :)


