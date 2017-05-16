
let initialState = {}

if (window.__INITIAL_STATE__.app) {
  initialState = window.__INITIAL_STATE__.app.data.electronicApi
}

export const electronic = (state = initialState) => {
  return state
}