let initialState = {}

if (window.__INITIAL_STATE__.app) {
  initialState = window.__INITIAL_STATE__.app.authenticate;
}

console.log(initialState)

export const authenticate = (state = initialState) => {
  return state
}