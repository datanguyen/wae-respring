let initialState = {}

if (window.__INITIAL_STATE__.app) {
  initialState = window.__INITIAL_STATE__.app.authenticate
}

export const authenticate = (state = initialState) => {
  return state
}